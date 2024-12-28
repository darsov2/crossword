import {Button} from "@/components/ui/button.tsx";
import React from "react";

const OnScreenKeyboard = ({selectedCell, handleCellInput}) => {

    const macedonianAlphabet = [
        ['Љ', 'Њ', 'Е', 'Р', 'Т', 'Ѕ', 'У', 'И', 'О', 'П', 'Ш', 'Ѓ', 'Ж'],
        ['А', 'С', 'Д', 'Ф', 'Г', 'Х', 'Ј', 'К', 'Л', 'Ч', 'Ќ'],
        ['З', 'Џ', 'Ц', 'В', 'Б', 'Н', 'М']
    ];

    return (<div className="inline-block space-y-2 mt-4">
        {macedonianAlphabet.map((row, i) => (
            <div key={i} className="flex justify-center gap-1">
                {row.map(key => (
                    <Button
                        key={key}
                        onClick={() => {
                            if (selectedCell) {
                                handleCellInput(selectedCell[0], selectedCell[1], key);
                            }
                        }}
                        className="w-10 h-10"
                        variant="outline"
                    >
                        {key}
                    </Button>
                ))}
            </div>
        ))}
    </div>);
}

export default OnScreenKeyboard;