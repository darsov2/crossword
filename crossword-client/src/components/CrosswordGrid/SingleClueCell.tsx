import React from "react";

const SingleClueCell = ({clue, handleClueClick}) => {
    return (
        <div
            className="text-xs text-center leading-tight px-1 cursor-pointer hover:bg-gray-200 w-full h-full flex items-center justify-center"
            onClick={() => handleClueClick(clue)}
        >
            {(clue).clue}
        </div>)
}

export default SingleClueCell;