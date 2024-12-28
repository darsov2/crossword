import {Calendar, User, UserCircle2, History} from 'lucide-react';
import {Card, CardContent} from '@/components/ui/card';
import {Button} from '@/components/ui/button';
import useGet from "@/hooks/useGet.ts";
import {useNavigate} from "react-router-dom";

const HomePage = () => {
    // This would typically come from your backend
    const todaysPuzzle = {
        difficulty: "Medium",
        gridSize: "15x15",
        theme: "World Capitals"
    };

    const {data, isLoading} = useGet('api/crossword/todays');
    const navigate = useNavigate();

    return (
        <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-4">
            <div className="max-w-md w-full space-y-8">
                {/* Title */}
                <div className="text-center">
                    <h1 className="text-4xl font-bold text-gray-900 mb-2">Крстозбор</h1>
                    <p className="text-gray-600">Нов крстозбор секој ден</p>
                </div>
                {!isLoading && (<Card className="bg-white shadow-lg">
                    <CardContent className="pt-6">
                        <div className="flex items-center justify-between mb-4">
                            <div className="flex items-center">
                                <Calendar className="h-5 w-5 text-blue-500 mr-2"/>
                                <h2 className="text-xl font-semibold">Денешен крстозбор - {data.date}</h2>
                            </div>
                        </div>
                        <div className="space-y-2 mb-6 text-sm text-gray-600">
                            <p>Тежина: {data.difficulty}</p>
                            <p>Димензии: {data.width} x {data.height}</p>
                        </div>

                        {/* Action Buttons */}
                        <div className="space-y-3">
                            <Button
                                className="w-full bg-blue-600 hover:bg-blue-700 text-white flex items-center justify-center"
                                onClick={() => navigate("/login")}
                            >
                                <UserCircle2 className="mr-2 h-5 w-5"/>
                                Играј најавен
                            </Button>

                            <Button
                                className="w-full bg-gray-600 hover:bg-gray-700 text-white flex items-center justify-center"
                                onClick={() => navigate("/todays")}
                            >
                                <User className="mr-2 h-5 w-5"/>
                                Играј како гостин
                            </Button>

                            <Button
                                variant="outline"
                                className="w-full border-gray-300 text-gray-700 hover:bg-gray-50 flex items-center justify-center"
                                onClick={() => navigate("/previous")}
                            >
                                <History className="mr-2 h-5 w-5"/>
                                Крстозбори од претходни денови
                            </Button>
                        </div>
                    </CardContent>
                </Card>)}
                {/* Today's Puzzle Card */}


                {/* Footer Info */}
                <p className="text-center text-sm text-gray-500">
                    Нов крстозбор е достапен секој ден по полноќ
                </p>
            </div>
        </div>
    );
};

export default HomePage;
