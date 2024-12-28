import React, {useState, useRef, useEffect} from 'react';
import {Button} from '@/components/ui/button';
import {Card} from '@/components/ui/card';
import ClueDisplay from "@/components/CrosswordGrid/ClueDisplay.tsx";
import SplitClueCell from "@/components/CrosswordGrid/SplitClueCell.tsx";
import OnScreenKeyboard from "@/components/CrosswordGrid/OnScreenKeyboard.tsx";
import SingleClueCell from "@/components/CrosswordGrid/SingleClueCell.tsx";
import useGet from "@/hooks/useGet.ts";
import {CrosswordGridResponse} from "@/interface/crossword-grid-response.interface.ts";

const ArrowWordPuzzle = () => {
    const [selectedCell, setSelectedCell] = useState(null);
    const [activeDirection, setActiveDirection] = useState('right');
    const [activeClue, setActiveClue] = useState(null);
    const [lastClickedCell, setLastClickedCell] = useState(null);
    const {data, isLoading} = useGet<CrosswordGridResponse>('api/crossword/todays/grid');
    const [puzzleData, setPuzzleData] = useState([]);

    const [grid, setGrid] = useState([]);

    let inputRefs = useRef(
        Array(8).fill(null).map(() => Array(8).fill(null))
    );

    const englishToMacedonian = {
        'a': 'А', 'b': 'Б', 'v': 'В', 'g': 'Г', 'd': 'Д', 'e': 'Е', 'z': 'З',
        'i': 'И', 'j': 'Ј', 'k': 'К', 'l': 'Л', 'm': 'М', 'n': 'Н', 'o': 'О',
        'p': 'П', 'r': 'Р', 's': 'С', 't': 'Т', 'u': 'У', 'f': 'Ф', 'h': 'Х',
        'c': 'Ц', 'q': 'Љ', 'w': 'Њ', 'x': 'Џ', 'y': 'Ѕ', '[': 'Ш', ']': 'Ѓ',
        ';': 'Ч', '\'': 'Ќ', '\\': 'Ж'
    };

    useEffect(() => {
        if (!isLoading) {
            if (data != null) {
                console.log(data)
                setPuzzleData(data.wordPlacements);
                inputRefs.current = Array(data?.height || 8).fill(null)
                    .map(() => Array(data?.width || 8).fill(null));

                setGrid(Array(data.height).fill().map(() => Array(data.width).fill({
                    value: '',
                    isClue: false
                })))
            }
        }
    }, [isLoading]);

    useEffect(() => {
        if (selectedCell) {
            const [row, col] = selectedCell;
            const inputElement = inputRefs.current[row][col]?.current;
            if (inputElement) {
                inputElement.focus();
            }
        }
    }, [selectedCell]);

    const findWordsForCell = (x, y) => {
        return puzzleData.filter(word =>
            word.cells.some(cell => cell.x === x && cell.y === y) ||
            (word.x === x && word.y === y)
        );
    };

    const findCluesForCell = (x, y) => {
        const rightClue = puzzleData.find(
            word => word.direction === 'right' &&
                (word.x === x && word.y === y)
        );

        const downClue = puzzleData.find(
            word => word.direction === 'down' &&
                (word.x === x && word.y === y)
        );

        return {rightClue, downClue};
    };

    const findNextCell = (currentX, currentY, direction) => {
        const currentWord = puzzleData.find(word =>
            word.direction === direction &&
            word.cells.some(cell => cell.x === currentX && cell.y === currentY)
        );

        if (!currentWord) return null;

        const currentCellIndex = currentWord.cells.findIndex(
            cell => cell.x === currentX && cell.y === currentY
        );

        if (currentCellIndex !== -1 && currentCellIndex < currentWord.cells.length - 1) {
            return currentWord.cells[currentCellIndex + 1];
        }

        return null;
    };

    const selectCell = (row, col, isClick = false) => {
        const words = findWordsForCell(col, row);
        if (!words.length) return;

        if (isClick) {
            const isSameCell = lastClickedCell &&
                lastClickedCell[0] === row &&
                lastClickedCell[1] === col;

            if (isSameCell && words.length > 1) {
                const newDirection = activeDirection === 'right' ? 'down' : 'right';
                const newWord = words.find(word => word.direction === newDirection);
                if (newWord) {
                    setActiveDirection(newDirection);
                    setActiveClue(newWord);
                }
            } else {
                const preferredWord = words.find(word => word.direction === activeDirection) || words[0];
                setActiveDirection(preferredWord.direction);
                setActiveClue(preferredWord);
            }
            setLastClickedCell([row, col]);
        }

        setSelectedCell([row, col]);
    };

    const handleClueClick = (clue) => {
        const firstCell = clue.cells[0];
        if (firstCell) {
            setActiveDirection(clue.direction);
            setActiveClue(clue);
            selectCell(firstCell.y, firstCell.x);
        }
    };

    const handleCellInput = (row, col, value) => {
        if (value.length > 1) return;

        const newGrid = [...grid];
        newGrid[row][col] = {...newGrid[row][col], value: value.toUpperCase()};
        setGrid(newGrid);

        if (value) {
            const nextCell = findNextCell(col, row, activeDirection);
            if (nextCell) {
                selectCell(nextCell.y, nextCell.x);
            }
        }
    };

    const handleKeyDown = (e, row, col) => {
        const navigateInDirection = (direction) => {
            let nextCell = null;

            if (direction === 'left') {
                const currentWord = puzzleData.find(word =>
                    word.direction === 'right' &&
                    word.cells.some(cell => cell.x === col && cell.y === row)
                );
                if (currentWord) {
                    const currentIndex = currentWord.cells.findIndex(
                        cell => cell.x === col && cell.y === row
                    );
                    if (currentIndex > 0) {
                        nextCell = currentWord.cells[currentIndex - 1];
                    }
                }
            } else if (direction === 'right') {
                nextCell = findNextCell(col, row, 'right');
            } else if (direction === 'up') {
                const currentWord = puzzleData.find(word =>
                    word.direction === 'down' &&
                    word.cells.some(cell => cell.x === col && cell.y === row)
                );
                if (currentWord) {
                    const currentIndex = currentWord.cells.findIndex(
                        cell => cell.x === col && cell.y === row
                    );
                    if (currentIndex > 0) {
                        nextCell = currentWord.cells[currentIndex - 1];
                    }
                }
            } else if (direction === 'down') {
                nextCell = findNextCell(col, row, 'down');
            }

            if (nextCell) {
                setActiveDirection(direction === 'left' || direction === 'right' ? 'right' : 'down');
                const words = findWordsForCell(nextCell.x, nextCell.y);
                const relevantWord = words.find(w =>
                    w.direction === (direction === 'left' || direction === 'right' ? 'right' : 'down')
                );
                if (relevantWord) {
                    setActiveClue(relevantWord);
                }
                selectCell(nextCell.y, nextCell.x);
            }
        };

        if (e.key === 'Backspace' && !grid[row][col].value) {
            const words = findWordsForCell(col, row);
            const currentWord = words.find(word => word.direction === activeDirection);

            if (currentWord) {
                const currentCellIndex = currentWord.cells.findIndex(
                    cell => cell.x === col && cell.y === row
                );

                if (currentCellIndex > 0) {
                    const prevCell = currentWord.cells[currentCellIndex - 1];
                    selectCell(prevCell.y, prevCell.x);
                }
            }
        } else if (e.key === 'ArrowLeft') {
            e.preventDefault();
            navigateInDirection('left');
        } else if (e.key === 'ArrowRight') {
            e.preventDefault();
            navigateInDirection('right');
        } else if (e.key === 'ArrowUp') {
            e.preventDefault();
            navigateInDirection('up');
        } else if (e.key === 'ArrowDown') {
            e.preventDefault();
            navigateInDirection('down');
        }
    };

    return (!isLoading && data != null && grid.length != 0 && (
            <div className="flex flex-col items-center justify-center min-h-screen p-4">
                <div className="w-auto mx-auto space-y-4">
                    <div className="flex justify-center">
                        <h1 className="text-2xl font-bold">Денешен крстозбор</h1>
                    </div>

                    <Card className="p-4 text-center min-h-[60px] flex items-center justify-center mb-4">
                        <ClueDisplay activeClue={activeClue}></ClueDisplay>
                    </Card>

                    <div className="flex flex-col items-center gap-4">
                        <div className="inline-block border border-gray-300">
                            {Array(data?.height).fill(null).map((_, rowIndex) => (
                                <div key={rowIndex} className="flex">
                                    {Array(data?.width).fill(null).map((_, colIndex) => {
                                        const {rightClue, downClue} = findCluesForCell(colIndex, rowIndex);
                                        const hasIntersectingClues = rightClue && downClue;

                                        const isSelected = selectedCell &&
                                            selectedCell[0] === rowIndex &&
                                            selectedCell[1] === colIndex;

                                        const isPartOfWord = puzzleData.some(word =>
                                            word.cells.some(cell => cell.x === colIndex && cell.y === rowIndex) ||
                                            (word.x === colIndex && word.y === rowIndex)
                                        );

                                        const isLastOfWord = puzzleData.find(word => {
                                            const lastChar = word.cells[word.cells.length - 1];
                                            return lastChar.x == colIndex && lastChar.y == rowIndex;
                                        })


                                        return (
                                            <div
                                                key={`${rowIndex}-${colIndex}`}
                                                className={`
                                              w-14 h-14 border border-gray-300
                                              flex flex-col items-center justify-center
                                              relative overflow-hidden
                                              ${rightClue || downClue ? 'bg-gray-100' : 'bg-white'}
                                              ${isSelected ? 'border-2 border-blue-500' : ''}
                                              ${!isPartOfWord ? 'bg-gray-80' : ''}
                                            `}
                                                onClick={() => {
                                                    if (isPartOfWord && !rightClue && !downClue) {
                                                        selectCell(rowIndex, colIndex, true);
                                                    }
                                                }}
                                            >
                                                {hasIntersectingClues ? (
                                                    <SplitClueCell rightClue={rightClue} downClue={downClue}
                                                                   handleClueClick={handleClueClick}/>
                                                ) : rightClue || downClue ? (
                                                    <SingleClueCell clue={rightClue || downClue}
                                                                    handleClueClick={handleClueClick}/>
                                                ) : (
                                                    <input
                                                        ref={el => {
                                                            inputRefs.current[rowIndex][colIndex] = {
                                                                current: el
                                                            };
                                                        }}
                                                        disabled={rowIndex == 0 && colIndex == 0}
                                                        type="text"
                                                        maxLength="1"
                                                        className="w-full h-full text-center text-xl font-bold bg-transparent outline-none cursor-pointer caret-transparent"
                                                        value={grid[rowIndex][colIndex].value}
                                                        onKeyDown={(e) => {
                                                            if (e.key.length === 1) {
                                                                e.preventDefault();
                                                                if (/[А-ШЃЖЅЈЉЊЌЏа-шѓжѕјљњќџ]/.test(e.key)) {
                                                                    handleCellInput(rowIndex, colIndex, e.key.toUpperCase());
                                                                } else if (/[a-zA-Z\[\]\\;']/.test(e.key)) {
                                                                    const macedonianLetter = englishToMacedonian[e.key.toLowerCase()];
                                                                    if (macedonianLetter) {
                                                                        handleCellInput(rowIndex, colIndex, macedonianLetter);
                                                                    }
                                                                }
                                                            } else if (e.key === 'Backspace') {
                                                                e.preventDefault();
                                                                const newGrid = [...grid];
                                                                newGrid[rowIndex][colIndex] = {
                                                                    ...newGrid[rowIndex][colIndex],
                                                                    value: ''
                                                                };
                                                                setGrid(newGrid);

                                                                if (!grid[rowIndex][colIndex].value) {
                                                                    const words = findWordsForCell(colIndex, rowIndex);
                                                                    const currentWord = words.find(word => word.direction === activeDirection);

                                                                    if (currentWord) {
                                                                        const currentCellIndex = currentWord.cells.findIndex(
                                                                            cell => cell.x === colIndex && cell.y === rowIndex
                                                                        );

                                                                        if (currentCellIndex > 0) {
                                                                            const prevCell = currentWord.cells[currentCellIndex - 1];
                                                                            selectCell(prevCell.y, prevCell.x);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                handleKeyDown(e, rowIndex, colIndex);
                                                            }
                                                        }}
                                                        onChange={() => {
                                                        }}
                                                        onFocus={() => selectCell(rowIndex, colIndex)}
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            selectCell(rowIndex, colIndex, true);
                                                        }}
                                                    />
                                                )}
                                            </div>
                                        );
                                    })}
                                </div>
                            ))}
                        </div>

                        <OnScreenKeyboard selectedCell={selectedCell} handleCellInput={handleCellInput}/>
                    </div>
                </div>
            </div>)
    );
};

export default ArrowWordPuzzle;