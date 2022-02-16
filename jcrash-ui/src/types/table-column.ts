import {LooseDictionary} from "quasar/dist/types/ts-helpers";

export interface TableColumnDefine {
    /**
     * Unique id, identifies column, (used by pagination.sortBy, 'body-cell-[name]' slot, ...)
     */
    name: string;
    /**
     * Label for header
     */
    label: string;
    /**
     * Row Object property to determine value for this column or function which maps to the required property
     */
    field: string | ((row: LooseDictionary) => any);
    /**
     * If we use visible-columns, this col will always be visible
     */
    required?: boolean;
    /**
     * Horizontal alignment of cells in this column
     * Default value: right
     */
    align?: "left" | "right" | "center";
    /**
     * Tell QTable you want this column sortable
     */
    sortable?: boolean;
    /**
     * Compare function if you have some custom data or want a specific way to compare two rows
     */
    sort?: (
        a: any,
        b: any,
        rowA: LooseDictionary,
        rowB: LooseDictionary
    ) => number;
    /**
     * Set column sort order: 'ad' (ascending-descending) or 'da' (descending-ascending); Overrides the 'column-sort-order' prop
     * Default value: ad
     */
    sortOrder?: "ad" | "da";
    /**
     * Function you can apply to format your data
     */
    format?: (val: any, row: LooseDictionary) => any;
    /**
     * Style to apply on normal cells of the column
     */
    style?: string | ((row: LooseDictionary) => string);
    /**
     * Classes to add on normal cells of the column
     */
    classes?: string | ((row: LooseDictionary) => string);
    /**
     * Style to apply on header cells of the column
     */
    headerStyle?: string;
    /**
     * Classes to add on header cells of the column
     */
    headerClasses?: string;
}