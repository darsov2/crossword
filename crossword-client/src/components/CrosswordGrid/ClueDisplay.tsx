import React from "react";

interface ClueDisplayProps {
    activeClue?: any
}

const ClueDisplay = (props: ClueDisplayProps) => {
    const activeClue = props.activeClue;

    return (<>
        {activeClue ? (
            <div>
                <span className="font-bold">{activeClue.clue}</span>
                <span className="ml-2 text-gray-500">
                ({activeClue.direction === 'right' ? 'надесно' : 'надолу'})
              </span>
            </div>
        ) : (
            <div className="text-gray-500">Изберете поле за да започнете</div>
        )}
    </>)
}

export default ClueDisplay;