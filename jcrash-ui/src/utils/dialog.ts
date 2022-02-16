import {Dialog, DialogChainObject, QDialogOptions} from 'quasar'

const showDialog = function(dialogConfig: QDialogOptions): DialogChainObject {
    return Dialog.create(dialogConfig)
}

export {showDialog}