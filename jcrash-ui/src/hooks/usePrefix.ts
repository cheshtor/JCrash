import {inject, provide, ref} from "vue";
import {Prefix} from "../types/prefix";
import {Ref} from "@vue/reactivity";
import {request} from "../utils/request/client";
import {showDialog} from "../utils/dialog";
import {showError, showSuccess} from "../utils/notify";

const PrefixSymbol = Symbol()

export const usePrefixProvider = () => {
    const prefixes = ref<Prefix[]>([])
    const setPrefixes = (value: Prefix[]) => prefixes.value = value

    provide(PrefixSymbol, {
        prefixes,
        setPrefixes
    })
}

export const usePrefix = () => {
    const prefixContext: {prefixes: Ref<Prefix[]>, setPrefixes: (value: Prefix[]) => void} | undefined = inject(PrefixSymbol)
    if (!prefixContext) {
        throw new Error('usePrefix Hook must be used after usePrefixProvider Hook')
    }
    const {prefixes, setPrefixes} = prefixContext

    const init = (): Prefix => {
        return {
            id: -1,
            name: '',
            description: ''
        }
    }

    const add = async (prefix: Prefix): Promise<boolean> => {
        const {data} = await request.post<boolean>('/api/prefix/add', prefix)
        return data
    }

    const edit = async (prefix: Prefix): Promise<boolean> => {
        const {data} = await request.post<boolean>('/api/prefix/edit', prefix)
        return data
    }

    const remove = async (prefixId: number): Promise<void> => {
        showDialog({
            title: '风险操作',
            message: '您确定要删除此前缀吗？',
            cancel: true,
            persistent: true
        }).onOk(async () => {
            const {data: success} = await request.get<boolean>(`/api/prefix/remove/${prefixId}`)
            if (success) {
                setPrefixes(prefixes.value.filter(prefix => prefix.id !== prefixId))
                showSuccess('前缀删除成功')
                return
            }
            showError("前缀删除失败")
            return
        })
    }

    const getById = async (prefixId: number): Promise<Prefix> => {
        const {data} = await request.get<Prefix>(`/api/prefix/get/${prefixId}`)
        return data
    }

    const listPrefixes = async (): Promise<Prefix[]> => {
        const {data} = await request.get<Prefix[]>('/api/prefix/list')
        setPrefixes(data)
        return data
    }

    // 能否点击新增按钮
    const addMore = (): boolean => {
        return prefixes.value.filter(prefix => prefix.id === -1).length === 0
    }

    // 清空所有 ID 为 -1 的前缀。因为这表示前缀处于新增状态，但还没有被保存
    const cleanUnSavedPrefix = (): void => {
        setPrefixes(prefixes.value.filter(prefix => prefix.id !== -1))
    }

    return {
        ...prefixContext,
        init,
        add,
        edit,
        remove,
        getById,
        listPrefixes,
        addMore,
        cleanUnSavedPrefix
    }
}