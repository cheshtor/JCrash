export interface PaginationConfig {
    page: number
    rowsPerPage: number
    rowsNumber: number
}

export const defaultPaginationConfig: PaginationConfig = {
    page: 1,
    rowsPerPage: 10,
    rowsNumber: 0
}