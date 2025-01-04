import React from "react";
import KeyboardKey from "@/components/CrosswordGrid/OnScreenKeyboard/KeyboardKey.tsx";

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
                    <KeyboardKey key={key} keyCode={key} selectedCell={selectedCell} handleCellInput={handleCellInput}/>
                ))}
            </div>
        ))}
    </div>);
}

export default OnScreenKeyboard;