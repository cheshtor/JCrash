export interface PageResult<T> {
    rows: Array<T>
    pageNo: number
    pageSize: number
    totalCount: number
    totalPages: number
}