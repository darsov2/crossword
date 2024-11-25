import React, { useState, useRef, useEffect } from 'react';
import { Button } from '@/components/ui/button';

const ArrowWordPuzzle = () => {
    const [selectedCell, setSelectedCell] = useState(null);
    const [grid, setGrid] = useState(Array(8).fill().map(() => Array(8).fill({
        value: '',
        isClue: false,
        direction: null
    })));

    // Initialize refs array properly with a 2D array structure
    const inputRefs = useRef(
        Array(8).fill().map(() => Array(8).fill(React.createRef()))
    );

    const puzzleData = [
        {
            x: 0, y: 0,
            clue: 'Mountain\ncat →',
            direction: 'right',
            answer: 'COUGAR',
            length: 6,
            cells: [{x: 1, y: 0}, {x: 2, y: 0}, {x: 3, y: 0}, {x: 4, y: 0}, {x: 5, y: 0}, {x: 6, y: 0}]
        },
        {
            x: 0, y: 1,
            clue: 'Morning\ndrink ↓',
            direction: 'down',
            answer: 'COFFEE',
            length: 6,
            cells: [{x: 0, y: 2}, {x: 0, y: 3}, {x: 0, y: 4}, {x: 0, y: 5}, {x: 0, y: 6}, {x: 0, y: 7}]
        }
    ];

    const findNextCell = (currentX, currentY, direction) => {
        const currentWord = puzzleData.find(word =>
            word.cells.some(cell => cell.x === currentX && cell.y === currentY)
        );

        if (!currentWord) return null;

        const currentCellIndex = currentWord.cells.findIndex(
            cell => cell.x === currentX && cell.y === currentY
        );

        if (currentCellIndex < currentWord.cells.length - 1) {
            const nextCell = currentWord.cells[currentCellIndex + 1];
            return { x: nextCell.x, y: nextCell.y };
        }

        return null;
    };

    const handleCellInput = (row, col, value) => {
        if (value.length > 1) return;

        const newGrid = [...grid];
        newGrid[row][col] = { ...newGrid[row][col], value: value.toUpperCase() };
        setGrid(newGrid);

        if (value) {
            const currentWord = puzzleData.find(word =>
                word.cells.some(cell => cell.x === col && cell.y === row)
            );

            if (currentWord) {
                const nextCell = findNextCell(col, row, currentWord.direction);
                if (nextCell && inputRefs.current[nextCell.y]?.[nextCell.x]?.current) {
                    inputRefs.current[nextCell.y][nextCell.x].current.focus();
                }
            }
        }
    };

    const handleKeyDown = (e, row, col) => {
        if (e.key === 'Backspace' && !grid[row][col].value) {
            const currentWord = puzzleData.find(word =>
                word.cells.some(cell => cell.x === col && cell.y === row)
            );

            if (currentWord) {
                const currentCellIndex = currentWord.cells.findIndex(
                    cell => cell.x === col && cell.y === row
                );

                if (currentCellIndex > 0) {
                    const prevCell = currentWord.cells[currentCellIndex - 1];
                    if (inputRefs.current[prevCell.y]?.[prevCell.x]?.current) {
                        inputRefs.current[prevCell.y][prevCell.x].current.focus();
                    }
                }
            }
        }
    };

    const handleVirtualKeyPress = (key) => {
        if (!selectedCell) return;
        const [row, col] = selectedCell;
        handleCellInput(row, col, key);
    };

    const CellArrow = ({ direction }) => {
        if (!direction) return null;

        const arrowStyles = {
            right: '→',
            down: '↓',
            diagonal: '↘'
        };

        return <span className="text-xs text-gray-600">{arrowStyles[direction]}</span>;
    };

    return (
        <div className="max-w-4xl mx-auto p-4 space-y-6">
            <div className="flex justify-between items-center mb-4">
                <h1 className="text-2xl font-bold">Arrow Word Puzzle</h1>
            </div>

            <div className="grid gap-0 border border-gray-300">
                {Array(8).fill().map((_, rowIndex) => (
                    <div key={rowIndex} className="flex">
                        {Array(8).fill().map((_, colIndex) => {
                            const clueData = puzzleData.find(
                                clue => clue.x === colIndex && clue.y === rowIndex
                            );

                            return (
                                <div
                                    key={`${rowIndex}-${colIndex}`}
                                    className={`
                    w-16 h-16 border border-gray-300
                    flex flex-col items-center justify-center
                    relative
                    ${clueData ? 'bg-gray-100' : 'bg-white'}
                  `}
                                >
                                    {clueData ? (
                                        <>
                                            <div className="text-xs text-center leading-tight">
                                                {clueData.clue.split('\n').map((line, i) => (
                                                    <div key={i}>{line}</div>
                                                ))}
                                            </div>
                                            <CellArrow direction={clueData.direction} />
                                        </>
                                    ) : (
                                        <input
                                            ref={inputRefs.current[rowIndex][colIndex]}
                                            type="text"
                                            maxLength="1"
                                            className="w-full h-full text-center text-xl font-bold uppercase bg-transparent outline-none"
                                            value={grid[rowIndex][colIndex].value}
                                            onChange={(e) => handleCellInput(rowIndex, colIndex, e.target.value)}
                                            onKeyDown={(e) => handleKeyDown(e, rowIndex, colIndex)}
                                            onFocus={() => setSelectedCell([rowIndex, colIndex])}
                                        />
                                    )}
                                </div>
                            );
                        })}
                    </div>
                ))}
            </div>

            {/* Virtual Keyboard */}
            <div className="space-y-2">
                {[
                    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
                    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'],
                    ['Z', 'X', 'C', 'V', 'B', 'N', 'M']
                ].map((row, i) => (
                    <div key={i} className="flex justify-center gap-1">
                        {row.map(key => (
                            <Button
                                key={key}
                                onClick={() => handleVirtualKeyPress(key)}
                                className="w-10 h-10"
                                variant="outline"
                            >
                                {key}
                            </Button>
                        ))}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ArrowWordPuzzle;