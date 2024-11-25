import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardHeader } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { User, Mail, Lock } from "lucide-react";
import usePost from '@/hooks/usePost';

const AuthPage = () => {
    const navigate = useNavigate();
    const [formErrors, setFormErrors] = useState({});
    const [registrationForm, setRegistrationForm] = useState({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: ''
    });

    const [loginForm, setLoginForm] = useState({
        email: '',
        password: ''
    });

    // Form validation functions
    const validateEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    };

    const validateName = (name) => {
        const nameRegex = /^[a-zA-Z\s-']+$/;
        return nameRegex.test(name) && name.length >= 2;
    };

    const validateUsername = (username) => {
        const usernameRegex = /^[a-zA-Z0-9_-]+$/;
        return usernameRegex.test(username) && username.length >= 3;
    };

    const validatePassword = (password) => {
        return password.length >= 8;
    };

    const validateRegistrationForm = () => {
        const errors = {};

        if (!validateUsername(registrationForm.username)) {
            errors.username = 'Корисничкото име мора да има најмалку 3 карактери и смее да содржи само латинични букви, бројки, долни црти и цртички';
        }
        if (!validateName(registrationForm.firstName)) {
            errors.firstName = 'Името смее да содржи само букви, цртички и апострофи';
        }
        if (!validateName(registrationForm.lastName)) {
            errors.lastName = 'Презимето смее да содржи само букви, цртички и апострофи';
        }
        if (!validateEmail(registrationForm.email)) {
            errors.email = 'Внесете валидна email адреса';
        }
        if (!validatePassword(registrationForm.password)) {
            errors.password = 'Лозинката мора да содржи најмалку 8 карактери';
        }

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const registerHook = usePost();
    const loginHook = usePost();

    const handleRegister = async (e) => {
        e.preventDefault();
        if (validateRegistrationForm()) {
            const response = await registerHook.createEntity('/api/auth/register', registrationForm);
            if (response != null) {
                navigate('/todays');
            }
        }
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        const response = await loginHook.createEntity('/api/auth/login', loginForm);
        if (response != null) {
            navigate('/todays');
        }
    };

    const handleInputChange = (e, formType) => {
        const { name, value } = e.target;
        if (formType === 'register') {
            setRegistrationForm(prev => ({ ...prev, [name]: value }));
        } else {
            setLoginForm(prev => ({ ...prev, [name]: value }));
        }
        // Clear error when user starts typing
        if (formErrors[name]) {
            setFormErrors(prev => ({ ...prev, [name]: '' }));
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-4">
            <div className="w-full max-w-md space-y-4">
                <div className="text-center space-y-2">
                    <h1 className="text-3xl font-bold text-gray-900">Дневен крстозбор</h1>
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
                                    <div className="space-y-2">
                                        <Label htmlFor="login-email">Email</Label>
                                        <div className="relative">
                                            <Mail className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="login-email"
                                                name="email"
                                                type="email"
                                                className="pl-10"
                                                value={loginForm.email}
                                                onChange={(e) => handleInputChange(e, 'login')}
                                                required
                                            />
                                        </div>
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="login-password">Лозинка</Label>
                                        <div className="relative">
                                            <Lock className="absolute left-3 top-3 h-4 w-4 text-gray-400" />
                                            <Input
                                                id="login-password"
                                                name="password"
                                                type="password"
                                                className="pl-10"
                                                value={loginForm.password}
                                                onChange={(e) => handleInputChange(e, 'login')}
                                                required
                                            />
                                        </div>
                                    </div>

                                    <Button
                                        type="submit"
                                        className="w-full"
                                        disabled={loginHook.isLoading}
                                    >
                                        {loginHook.isLoading ? "Најава..." : "Најави се"}
                                    </Button>
                                </form>
                            </TabsContent>

                            <TabsContent value="register">
                                <form onSubmit={handleRegister} className="space-y-4">
                                    <div className="space-y-2">
                                        <Label htmlFor="username">Корисничко име</Label>
                                        <div className="relative">
                                            <User className="absolute left-3 top-3 h-4 w-4 text-gray-400"/>
                                            <Input
                                                id="username"
                                                name="username"
                                                type="text"
                                                className="pl-10"
                                                value={registrationForm.username}
                                                onChange={(e) => handleInputChange(e, 'register')}
                                                required
                                            />
                                        </div>
                                        {formErrors.username && (
                                            <Alert variant="destructive">
                                                <AlertDescription>{formErrors.username}</AlertDescription>
                                            </Alert>
                                        )}
                                    </div>

                                    <div className="grid grid-cols-2 gap-4">
                                        <div className="space-y-2">
                                            <Label htmlFor="firstName">Име</Label>
                                            <Input
                                                id="firstName"
                                                name="firstName"
                                                type="text"
                                                value={registrationForm.firstName}
                                                onChange={(e) => handleInputChange(e, 'register')}
                                                required
                                            />
                                            {formErrors.firstName && (
                                                <Alert variant="destructive">
                                                    <AlertDescription>{formErrors.firstName}</AlertDescription>
                                                </Alert>
                                            )}
                                        </div>

                                        <div className="space-y-2">
                                            <Label htmlFor="lastName">Презиме</Label>
                                            <Input
                                                id="lastName"
                                                name="lastName"
                                                type="text"
                                                value={registrationForm.lastName}
                                                onChange={(e) => handleInputChange(e, 'register')}
                                                required
                                            />
                                            {formErrors.lastName && (
                                                <Alert variant="destructive">
                                                    <AlertDescription>{formErrors.lastName}</AlertDescription>
                                                </Alert>
                                            )}
                                        </div>
                                    </div>

                                        <div className="space-y-2">
                                            <Label htmlFor="register-email">Email</Label>
                                            <div className="relative">
                                                <Mail className="absolute left-3 top-3 h-4 w-4 text-gray-400"/>
                                                <Input
                                                    id="register-email"
                                                    name="email"
                                                    type="email"
                                                    className="pl-10"
                                                    value={registrationForm.email}
                                                    onChange={(e) => handleInputChange(e, 'register')}
                                                    required
                                                />
                                            </div>
                                            {formErrors.email && (
                                                <Alert variant="destructive">
                                                    <AlertDescription>{formErrors.email}</AlertDescription>
                                                </Alert>
                                            )}
                                        </div>

                                        <div className="space-y-2">
                                            <Label htmlFor="register-password">Лозинка</Label>
                                            <div className="relative">
                                                <Lock className="absolute left-3 top-3 h-4 w-4 text-gray-400"/>
                                                <Input
                                                    id="register-password"
                                                    name="password"
                                                    type="password"
                                                    className="pl-10"
                                                    value={registrationForm.password}
                                                    onChange={(e) => handleInputChange(e, 'register')}
                                                    required
                                                />
                                            </div>
                                            {formErrors.password && (
                                                <Alert variant="destructive">
                                                    <AlertDescription>{formErrors.password}</AlertDescription>
                                                </Alert>
                                            )}
                                        </div>

                                        <Button
                                            type="submit"
                                            className="w-full"
                                            disabled={registerHook.isLoading}
                                        >
                                            {registerHook.isLoading ? "Регистрирај се..." : "Регистрирај се"}
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
                                onClick={() => navigate('/crossword')}
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