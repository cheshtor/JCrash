import {LooseDictionary} from "quasar/dist/types/ts-helpers";

export interface TableFetchDataPagination {
    sortBy?: string
    descending?: boolean
    page?: number
    rowsPerPage?: number
}
export interface TableFetchDataProps {
    pagination: TableFetchDataPagination,
    filter?: (
        rows: any[],
        terms: string | LooseDictionary,
        cols: any[],
        getCellValue: (col: LooseDictionary, row: LooseDictionary) => any
    ) => any[];
}