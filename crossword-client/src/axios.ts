import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/",
    withCredentials: true,
    maxRedirects: 1,
    beforeRedirect: (options, { headers }) => {
        if (options.hostname === "example.com") {
            options.auth = "user:password";
        }}

})

export default instance
