import {useContext, useEffect, useState} from "react";
import instance from "@/axios.ts";
import AuthContext from "@/components/AuthContext.ts";
import {UserResponse} from "@/interface/user-response.ts";
import axios from "@/axios.ts";

export const AuthProvider = ({children}) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    checkAuthStatus();
  }, []);

  const checkAuthStatus = async () => {
    try {
      const {data} = await instance.get<UserResponse>('/api/auth/status');
      setUser(data);
      setError(null);
    } catch (err) {
      setUser(null);
      setError(err.response?.data?.message || 'Error checking auth status');
    } finally {
      setLoading(false);
    }
  };

  const login = async (username, password) => {
    try {
      console.log('LOGIN VO AUTH')
      const {data} = await axios.post('http://localhost:8080/api/auth/login', {
        username,
        password,
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      });
      setUser(data);
      setError(null);
      return true;
    } catch (err) {
      setError(err.response?.data?.message || 'Login failed');
      return false;
    }
  };

  const register = async (userData) => {
    try {
      const {data} = await instance.post<UserResponse>('/api/auth/register', userData);
      setUser(data);
      setError(null);
      return true;
    } catch (err) {
      setError(err.response?.data?.message || 'Registration failed');
      return false;
    }
  };

  const logout = async () => {
    try {
      await instance.post('/api/auth/logout');
      setUser(null);
      setError(null);
      return true;
    } catch (err) {
      setError(err.response?.data?.message || 'Logout failed');
      return false;
    }
  };

  useEffect(() => {
    const interceptor = instance.interceptors.response.use(
        (response) => response,
        async (error) => {
          if (error.response?.status === 401) {
            setUser(null);
            // You could redirect to login page here if needed
          }
          return Promise.reject(error);
        }
    );

    return () => {
      instance.interceptors.response.eject(interceptor);
    };
  }, []);

  const value = {
    user,
    loading,
    error,
    login,
    register,
    logout,
    checkAuthStatus,
  };

  return (
      <AuthContext.Provider value={value}>
        {!loading && children}
      </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};