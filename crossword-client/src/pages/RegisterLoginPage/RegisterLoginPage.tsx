import React, { useState } from 'react';
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardHeader } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { User, Key, Mail, Lock, UserCircle } from "lucide-react";
import usePost from '@/hooks/usePost';
import axios from "@/axios.ts";
import { useNavigate } from 'react-router-dom';

// Validation functions
const validateEmail = (email: string) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
};

const validateName = (name: string) => {
    const nameRegex = /^[a-zA-Z\s-']+$/;
    return nameRegex.test(name) && name.length >= 2;
};

const validateUsername = (username: string) => {
    const usernameRegex = /^[a-zA-Z0-9_-]+$/;
    return usernameRegex.test(username) && username.length >= 3;
};

const validatePassword = (password: string) => {
    return password.length >= 6;
};

const AuthPage = () => {
    const navigate = useNavigate();

    // Form states
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: ''
    });

    const [loginData, setLoginData] = useState({
        email: '',
        password: ''
    });

    // Validation states
    const [errors, setErrors] = useState({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: ''
    });

    const [loginErrors, setLoginErrors] = useState({
        email: '',
        password: ''
    });

    const [registrationError, setRegistrationError] = useState('');
    const [loginError, setLoginError] = useState('');
    const { isLoading } = usePost('/api/auth/register');

    const validateRegistrationForm = () => {
        const newErrors = {
            username: !validateUsername(formData.username) ? 'Корисничкото име мора да има најмалку три карактери и смее да содржи само латинични букви, бројки, долни црти и цртички' : '',
            password: !validatePassword(formData.password) ? 'Лозинката мора да содржи најмалку шест карактери' : '',
            firstName: !validateName(formData.firstName) ? 'Името смее да содржи само букви, цртички и апострофи' : '',
            lastName: !validateName(formData.lastName) ? 'Презимето смее да содржи само букви, цртички и апострофи' : '',
            email: !validateEmail(formData.email) ? 'Внесете валидна email адреса' : ''
        };

        setErrors(newErrors);
        return Object.values(newErrors).every(error => error === '');
    };

    const validateLoginForm = () => {
        const newErrors = {
            email: !validateEmail(loginData.email) ? 'Внесете валидна email адреса' : '',
            password: loginData.password.length === 0 ? 'Лозинката е задолжителна' : ''
        };

        setLoginErrors(newErrors);
        return Object.values(newErrors).every(error => error === '');
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
        // Clear error when user starts typing
        setErrors(prev => ({
            ...prev,
            [name]: ''
        }));
    };

    const handleLoginInputChange = (e) => {
        const { name, value } = e.target;
        setLoginData(prev => ({
            ...prev,
            [name]: value
        }));
        // Clear error when user starts typing
        setLoginErrors(prev => ({
            ...prev,
            [name]: ''
        }));
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        setLoginError('');

        if (!validateLoginForm()) {
            return;
        }

        try {
            const response = await axios.post('/api/auth/login', loginData);
            // Assuming the API returns a token
            localStorage.setItem('token', response.data.token);
            // Navigate to crossword page
            navigate('/todays');
        } catch (error) {
            setLoginError(
                error.response?.data?.message || 'Најавата беше неуспешна. Проверете ги корисничкото име и лозинката.'
            );
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        setRegistrationError('');

        if (!validateRegistrationForm()) {
            return;
        }

        try {
            const response = await axios.post('/api/auth/register', formData);
            // Assuming the API returns a token upon successful registration
            localStorage.setItem('token', response.data.token);
            // Navigate to crossword page
            navigate('/todays');
        } catch (error) {
            setRegistrationError(
                error.response?.data?.message || 'Регистрацијата беше неуспешна. Ве молиме обидете се повторно.'
            );
        }
    };

    const handleGuestPlay = () => {
        navigate('/todays');
    };

    return (
        <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-4">
            <div className="w-full max-w-md space-y-4">
                <div className="text-center space-y-2">
                    <h1 className="text-3xl font-bold text-gray-900">Daily Crossword</h1>
                    <p className="text-gray-500">Најавете се или регистрирајте се за да го следите вашиот напредок</p>
                </div>

                <Card>
                    <Tabs defaultValue="login" className="w-full">
                        <CardHeader>
                            <TabsList className="grid w-full grid-cols-2">
                                <TabsTrigger value="login">Најава</TabsTrigger>
                                <TabsTrigger value="register">Регистрација</TabsTrigger>
                            </TabsList>
                        </CardHeader>

                        <CardContent className="p-6">
                            <TabsContent value="login">
                                <form onSubmit={handleLogin} className="space-y-4">
                                    {loginError && (
                                        <div className="text-red-500 text-sm p-2 bg-red-50 rounded">
                                            {loginError}
                                        </div>
                                    )}

                                    <div className="space-y-2">
                                        <Label htmlFor="login-email">Email</Label>
                                        <div className="relative">
                                            <Mail className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="login-email"
                                                name="email"
                                                type="email"
                                                value={loginData.email}
                                                onChange={handleLoginInputChange}
                                                className="pl-10"
                                                required
                                            />
                                        </div>
                                        {loginErrors.email && (
                                            <p className="text-red-500 text-sm">{loginErrors.email}</p>
                                        )}
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="login-password">Лозинка</Label>
                                        <div className="relative">
                                            <Lock className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="login-password"
                                                name="password"
                                                type="password"
                                                value={loginData.password}
                                                onChange={handleLoginInputChange}
                                                className="pl-10"
                                                required
                                            />
                                        </div>
                                        {loginErrors.password && (
                                            <p className="text-red-500 text-sm">{loginErrors.password}</p>
                                        )}
                                    </div>

                                    <Button type="submit" className="w-full">
                                        Sign in
                                    </Button>
                                </form>
                            </TabsContent>

                            <TabsContent value="register">
                                <form onSubmit={handleRegister} className="space-y-4">
                                    {registrationError && (
                                        <div className="text-red-500 text-sm p-2 bg-red-50 rounded">
                                            {registrationError}
                                        </div>
                                    )}

                                    <div className="grid grid-cols-2 gap-4">
                                        <div className="space-y-2">
                                            <Label htmlFor="firstName">Име</Label>
                                            <div className="relative">
                                                <UserCircle className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                                <Input
                                                    id="firstName"
                                                    name="firstName"
                                                    value={formData.firstName}
                                                    onChange={handleInputChange}
                                                    className="pl-10"
                                                    required
                                                />
                                            </div>
                                            {errors.firstName && (
                                                <p className="text-red-500 text-sm">{errors.firstName}</p>
                                            )}
                                        </div>

                                        <div className="space-y-2">
                                            <Label htmlFor="lastName">Презиме</Label>
                                            <div className="relative">
                                                <UserCircle className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                                <Input
                                                    id="lastName"
                                                    name="lastName"
                                                    value={formData.lastName}
                                                    onChange={handleInputChange}
                                                    className="pl-10"
                                                    required
                                                />
                                            </div>
                                            {errors.lastName && (
                                                <p className="text-red-500 text-sm">{errors.lastName}</p>
                                            )}
                                        </div>
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="username">Корисничко име</Label>
                                        <div className="relative">
                                            <User className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="username"
                                                name="username"
                                                value={formData.username}
                                                onChange={handleInputChange}
                                                className="pl-10"
                                                required
                                            />
                                        </div>
                                        {errors.username && (
                                            <p className="text-red-500 text-sm">{errors.username}</p>
                                        )}
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="email">Email</Label>
                                        <div className="relative">
                                            <Mail className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="email"
                                                name="email"
                                                type="email"
                                                value={formData.email}
                                                onChange={handleInputChange}
                                                className="pl-10"
                                                required
                                            />
                                        </div>
                                        {errors.email && (
                                            <p className="text-red-500 text-sm">{errors.email}</p>
                                        )}
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="password">Лозинка</Label>
                                        <div className="relative">
                                            <Key className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="password"
                                                name="password"
                                                type="password"
                                                value={formData.password}
                                                onChange={handleInputChange}
                                                className="pl-10"
                                                required
                                            />
                                        </div>
                                        {errors.password && (
                                            <p className="text-red-500 text-sm">{errors.password}</p>
                                        )}
                                    </div>

                                    <Button
                                        type="submit"
                                        className="w-full"
                                        disabled={isLoading}
                                    >
                                        {isLoading ? "Регистрација..." : "Регистрација"}
                                    </Button>
                                </form>
                            </TabsContent>
                        </CardContent>
                    </Tabs>
                </Card>

                <Card>
                    <CardContent className="p-6">
                        <div className="text-center space-y-4">
                            <CardDescription>
                                Не сакате да се регистрирате? Сепак може да играте!
                            </CardDescription>
                            <Button
                                variant="outline"
                                className="w-full"
                                onClick={handleGuestPlay}
                            >
                                Играј како гостин
                            </Button>
                        </div>
                    </CardContent>
                </Card>
            </div>
        </div>
    );
};

export default AuthPage;