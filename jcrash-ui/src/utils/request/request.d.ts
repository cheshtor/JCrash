import * as axios from 'axios'

declare module 'axios' {
    export interface LeafResult<T> {
        success: boolean
        code: number
        message: string
        data: T
    }
}