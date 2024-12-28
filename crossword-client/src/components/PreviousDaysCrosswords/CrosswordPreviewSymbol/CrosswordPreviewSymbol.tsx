const CrosswordPreviewSymbol = ({size = 5}) => {
    const pattern = [
        [1, 1, 1, 0, 1],
        [1, 0, 1, 0, 1],
        [1, 1, 1, 1, 1],
        [1, 0, 1, 0, 1],
        [1, 0, 1, 1, 1],
    ];

    return (
        <div className="w-16 h-16 mx-auto">
            <div className="grid grid-cols-5 gap-px bg-gray-200 p-0.5">
                {pattern.map((row, i) =>
                    row.map((cell, j) => (
                        <div
                            key={`${i}-${j}`}
                            className={`aspect-square ${
                                cell ? 'bg-white' : 'bg-gray-800'
                            }`}
                        />
                    ))
                )}
            </div>
        </div>
    );
};

export default CrosswordPreviewSymbol;