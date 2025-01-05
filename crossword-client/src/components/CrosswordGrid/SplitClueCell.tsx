import React from "react";

const SplitClueCell = ({rightClue, downClue, handleClueClick}) => (
    <div className="w-full h-full relative">
        <div className="absolute left-0 top-0 w-full h-[50%] border-b border-gray-300"/>

        <div
            className="absolute top-0 left-0 w-full max-h-full h-[50%] flex items-center justify-center p-1 cursor-pointer hover:bg-gray-200"
            onClick={() => handleClueClick(rightClue)}
        >
            <div className="text-xxs max-h-full break-all overflow-hidden text-ellipsis text-center leading-none">
                {rightClue.clue}
            </div>
        </div>

        <div
            className="absolute bottom-0 left-0 w-full max-h-full h-[50%] flex items-center justify-center p-1 cursor-pointer hover:bg-gray-200"
            onClick={() => handleClueClick(downClue)}
        >
            <div className="text-xxs max-h-full break-all overflow-hidden text-ellipsis text-center leading-none">
                {downClue.clue}
            </div>
        </div>
    </div>
);

export default SplitClueCell;