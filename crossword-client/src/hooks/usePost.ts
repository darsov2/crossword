import { useState } from 'react';
import axios from "@/axios.ts";
import { UserResponse } from "@/interface/user-response.ts";

const usePost = () => {
    const [data, setData] = useState<UserResponse | null>(null);
    const [isLoading, setIsLoading] = useState(false);

    const createEntity = async (url: string, entity: any): Promise<UserResponse | null> => {
        try {
            setIsLoading(true);
            const response = await axios.post(url, entity);
            setData(response.data);
            return response.data;
        } catch (error) {
            console.error('Error in usePost:', error);
            return null;
        } finally {
            setIsLoading(false);
        }
    };

    return { data, isLoading, createEntity };
};

export default usePost;