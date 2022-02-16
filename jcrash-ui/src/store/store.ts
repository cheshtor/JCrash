import {createStore, Store, useStore as baseUseStore} from "vuex";
import {InjectionKey} from "vue";

export interface StateType {
    prevPageTitle: string
}

export const key: InjectionKey<Store<StateType>> = Symbol()

export const store = createStore<StateType>({
    state: {
        prevPageTitle: 'JCrash',
    },
    mutations: {
        saveCurrentPageTitle(state: StateType, title: string) {
            state.prevPageTitle = title
        }
    },
    getters: {
        getPrevPageTitle(state: StateType, getters) {
            return state.prevPageTitle
        }
    },
    actions: {

    }
})

export function useStore() {
    return baseUseStore(key)
}