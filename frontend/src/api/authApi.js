import {apiFetch, apiPost} from "./apiClient.js";

export function postLoginData(formData){
    return apiPost("api/auth/login", formData);
}