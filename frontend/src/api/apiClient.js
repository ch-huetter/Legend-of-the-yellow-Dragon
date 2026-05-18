const baseUrl = import.meta.env.VITE_BACKEND_URL;
const mode = import.meta.env.MODE;

export async function apiFetch(path, options = {}, errorHandler = handleError) {
    const response = await fetch(path, {
        credentials: "include",
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        },
        ...options
    });
    await errorHandler(response);

    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
        return response.json();
    }

    return response.text();
}

export async function apiPost(path, data = {}, options = {}) {
    const headers = {
        "Content-Type": "application/json",
        ...(options.headers || {})
    }
    await apiFetch("/api/game/csrf").then((res) => {
        headers["X-XSRF-TOKEN"] = res.token;
    });

    const response = await fetch(path, {
        credentials: "include",
        ...options,
        method: "POST",
        body: data ? JSON.stringify(data) : undefined,
        headers: headers
    });

    await handleError(response);

    const contentType = response.headers.get("content-type");

    if (contentType && contentType.includes("application/json")) {
        return response.json();
    }

    return response;
}

async function handleError(response) {
    if (response.status === 401) {
        window.location.href = "/";
        throw new Error("Nicht eingeloggt");
    }

    if (!response.ok) {
        const text = await response.text();
        throw new Error(`API Fehler ${response.status}: ${text}`);
    }
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
        return parts.pop().split(";").shift();
    }
    return null;
}