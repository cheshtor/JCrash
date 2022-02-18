import {inject, provide, ref} from "vue";
import {Project} from "../types/project";
import {Ref} from "@vue/reactivity";
import {TableColumnDefine} from "../types/table-column";
import {datetimeFormat} from "../utils/time";

const ProjectSymbol = Symbol()

export const useProjectProvider = () => {
    const projects = ref<Project[]>([])
    const setProjects = (value: Project[]) => projects.value = value

    provide(ProjectSymbol, {
        projects,
        setProjects
    })
}

export const useProject = () => {
    const projectContext: {projects: Ref<Project[]>, setProjects: (value: Project[]) => void} | undefined = inject(ProjectSymbol)
    if (!projectContext) {
        throw new Error('useProject Hook must be used after useProjectProvider Hook')
    }
    const {projects, setProjects} = projectContext

    /**
     * 表格列定义
     */
    const tableColumns: Array<TableColumnDefine> = [
        {name:'name',align:'center',label:'项目名称',field:'name'},
        {name:'createTime',align:'center',label:'创建时间',field:'createTime',format:(val:any)=>{return datetimeFormat(val)}},
        {name:'action',align:'center',label:'操作',field:'action'}
    ]

    return {
        ...projectContext
    }
}