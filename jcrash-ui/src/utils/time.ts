import dayjs from "dayjs";

export const datetimeFormat = (datetime: dayjs.ConfigType): string => {
    return dayjs(datetime).format('YYYY-MM-DD HH:mm:ss')
}