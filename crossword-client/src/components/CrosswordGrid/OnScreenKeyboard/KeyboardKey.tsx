import {Button} from "@/components/ui/button.tsx";
import React from "react";

const KeyboardKey = ({keyCode, selectedCell, handleCellInput}) => {
    return (
        <Button
            key={keyCode}
            onClick={() => {
                if (selectedCell) {
                    handleCellInput(selectedCell[0], selectedCell[1], keyCode);
                }
            }}
            className="w-10 h-10"
            variant="outline"
        >
            {keyCode}
        </Button>
    )
}

export default KeyboardKey;