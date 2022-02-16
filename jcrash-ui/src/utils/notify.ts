import {Notify} from "quasar";

const showAlert = (type: string, icon: string, message: string) => {
    Notify.create({
        position: 'top',
        progress: true,
        timeout: 1000,
        type,
        icon,
        message
    })
}

const showSuccess = (message: string) => {
    showAlert('positive', 'check_circle', message)
}

const showWarning = (message: string) => {
    showAlert('warning', 'warning', message)
}

const showError = (message: string) => {
    showAlert('negative', 'dangerous', message)
}

export {showAlert, showSuccess, showWarning, showError}