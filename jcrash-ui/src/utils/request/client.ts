import axios, {AxiosResponse, LeafResult} from "axios";
import {Get, Post} from "./method-type";
import {showError} from "../notify";

const service = axios.create({
    timeout: 6000,
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }
})

service.interceptors.response.use(<T, D>(response: AxiosResponse<LeafResult<T>, D>) => {
    if (response.data.success) {
        return response
    }
    showError(response.data.message)
    return Promise.reject(response.data.message)
}, error => {
    showError(`请求发生异常：${error}`)
    return Promise.reject(error)
})

const get: Get = async (url, params, config) => {
    const response = await service.get(url, {params, ...config})
    return response.data
}

const post: Post = async (url, data, config) => {
    const response = await service.post(url, data, config)
    return response.data
}

const request = {get, post}

export {request}