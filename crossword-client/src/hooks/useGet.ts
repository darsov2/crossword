import {useState, useEffect} from 'react';
import axios from "@/axios.ts";
import {TodaysCrosswordResponse} from "@/interface/todays-crossword-response.ts";

const useGet = (url: string) => {

    const [data, setData] = useState((null as TodaysCrosswordResponse));
    const [isLoading, setIsLoading] = useState(true);
    const getData = async (url: string) => {
        await axios.get(url, {maxRedirects: 0}).then((res) => {
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


export default useGet;
