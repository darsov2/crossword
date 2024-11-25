import {useState, useEffect} from 'react';
import axios from "@/axios.ts";
import {UserResponse} from "@/interface/user-response.ts";

const usePost = (url: string) => {

    const [data, setData] = useState((null as UserResponse));
    const [isLoading, setIsLoading] = useState(true);
    const getData = async (url: string) => {
        await axios.post(url, {maxRedirects: 0}).then((res) => {
            setData(res.data);
        }).catch((error) => {
            console.log(error)
        })
            .finally(() => {
                setIsLoading(false);
            });
    };

    useEffect(() => {
        setIsLoading(true);
        getData(url);
    }, [url]);

    return {
        data,
        setData,
        isLoading,
        getData
    };
};


export default usePost;
