import { useState, useEffect } from 'react';
import axios, { AxiosError, AxiosResponse } from '@/axios.ts';

const useGet = <T>(url: string, defaultValue: T | null = null) => {
    const [data, setData] = useState<T | null>(defaultValue);
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const getData = async (url: string) => {
        try {
            setIsLoading(true);
            setError(null);
            const response: AxiosResponse<T> = await axios.get(url, { maxRedirects: 0 });
            setData(response.data);
        } catch (err) {
            const axiosError = err as AxiosError;
            setError(axiosError.message || 'An error occurred');
            console.error(axiosError);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        const controller = new AbortController();
        const fetchData = async () => {
            try {
                await getData(url);
            } catch {
                setError('Fetch was cancelled.');
            }
        };
        fetchData();

        return () => {
            controller.abort();
        };
    }, [url]);

    return {
        data,
        setData,
        isLoading,
        error,
        getData,
    };
};

export default useGet;
