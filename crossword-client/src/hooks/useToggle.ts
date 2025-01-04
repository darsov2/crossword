import {useReducer} from "react";

const useToggle = (defaultValue: boolean) => {
    return useReducer((value) => !value, !!defaultValue)
}

export default useToggle;