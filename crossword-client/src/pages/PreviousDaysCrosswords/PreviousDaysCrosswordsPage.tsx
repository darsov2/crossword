import {useState} from "react";
import {Button} from "@/components/ui/button.tsx";
import {ArrowLeft} from "lucide-react";
import {Card, CardContent} from "@/components/ui/card.tsx";
import CrosswordPreviewSymbol
    from "@/components/PreviousDaysCrosswords/CrosswordPreviewSymbol/CrosswordPreviewSymbol.tsx";
import CrosswordCard from "@/pages/PreviousDaysCrosswords/CrosswordCard/CrosswordCard.tsx";
import {useNavigate} from "react-router-dom";
import useGet from "@/hooks/useGet.ts";
import {TodaysCrosswordResponse} from "@/interface/todays-crossword-response.ts";

const PreviousDaysCrosswordsPage = () => {

    const navigate = useNavigate();
    const { data, isLoading } = useGet<TodaysCrosswordResponse[]>('api/crossword/previous');

    const dummyCrosswords = [
        {
            id: '1',
            date: '28 декември',
            difficulty: 'Лесно',
            theme: '#1',
            width: 15,
            height: 15,
            completionRate: 85
        },
        {
            id: '2',
            date: '27 декември',
            difficulty: 'Средно',
            theme: '#2',
            width: 15,
            height: 15,
            completionRate: 72
        },
        {
            id: '3',
            date: '26 декември',
            difficulty: 'Тешко',
            theme: '#3',
            width: 20,
            height: 20,
            completionRate: 45
        },
        {
            id: '4',
            date: '25 декември',
            difficulty: 'Средно',
            theme: '#4',
            width: 15,
            height: 15,
            completionRate: 68
        },
        {
            id: '5',
            date: '24 декември',
            difficulty: 'Лесно',
            theme: '#5',
            width: 12,
            height: 12,
            completionRate: 92
        },
        {
            id: '6',
            date: '23 декември',
            difficulty: 'Тешко',
            theme: '#6',
            width: 20,
            height: 20,
            completionRate: 38
        }
    ];

    const filteredCrosswords = dummyCrosswords;

    return (
        <div className="min-h-screen bg-gray-50 p-4">
            <div className="max-w-5xl mx-auto">
                <div className="mb-6">
                    <Button
                        variant="ghost"
                        className="mb-4 text-gray-600 hover:text-gray-900"
                        onClick={() => navigate('/')}
                    >
                        <ArrowLeft className="h-5 w-5 mr-2"/>
                        Назад
                    </Button>
                    <h1 className="text-3xl font-bold text-gray-900">Крстозбори од претходни денови</h1>
                </div>

                <div className="grid gap-5 grid-cols-5">
                    {!isLoading && data!.map((puzzle, i) => (
                        <CrosswordCard key={i} puzzle={puzzle} onNavigate={navigate}/>
                    ))}
                </div>

                {filteredCrosswords.length === 0 && (
                    <div className="text-center py-8">
                        <p className="text-gray-600">Нема пронајдени крстозбори</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default PreviousDaysCrosswordsPage;