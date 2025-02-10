import {Card, CardContent} from "@/components/ui/card.tsx";
import {Button} from "@/components/ui/button";
import CrosswordPreviewSymbol from "@/components/PreviousDaysCrosswords/CrosswordPreviewSymbol/CrosswordPreviewSymbol.tsx";
import CompletionRateBar from "@/pages/PreviousDaysCrosswords/CrosswordCard/CompletionRateBar.tsx";
import moment from 'moment';
import 'moment/locale/mk';

const CrosswordCard = ({puzzle, onNavigate}) => {
    return (
        <Card
            key={puzzle.id}
            className="aspect-square bg-white hover:shadow-lg transition-shadow cursor-pointer relative flex flex-col"
            onClick={() => onNavigate(`/crossword/${puzzle.id}`)}
        >
            <CardContent className="p-2 flex flex-col h-full">
                <div className="flex justify-between items-center mb-1">
                    <div className="text-xs text-gray-600">{moment(puzzle.date).locale('mk').format('DD MMMM')}</div>
                    <Button
                        variant="ghost"
                        size="sm"
                        className="h-6 px-2 text-xs"
                        onClick={(e) => {
                            e.preventDefault();
                            onNavigate(`/stats/${puzzle.id}`);
                        }}
                    >
                        Статистика
                    </Button>
                </div>

                <div className="flex-grow flex flex-col items-center justify-center space-y-2">
                    <CrosswordPreviewSymbol/>
                    <span className="text-xs text-gray-600">{puzzle.width} x {puzzle.height}</span>
                </div>

                <div className="mt-1">
                    <div className="flex items-center justify-between mb-1">
                        <span className="text-xs font-medium truncate">{puzzle.theme}</span>
                        <span className={`px-1.5 py-0.5 rounded-full text-xs ${
                            puzzle.difficulty === 'Лесно' ? 'bg-green-100 text-green-800' :
                                puzzle.difficulty === 'Средно' ? 'bg-yellow-100 text-yellow-800' :
                                    'bg-red-100 text-red-800'
                        }`}>
                            {puzzle.difficulty}
                        </span>
                    </div>
                    <CompletionRateBar rate={puzzle.completionRate}></CompletionRateBar>
                </div>
            </CardContent>
        </Card>
    )
}

export default CrosswordCard;