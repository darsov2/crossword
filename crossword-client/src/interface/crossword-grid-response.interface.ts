import {CrosswordWordPlacementResponse} from "@/interface/crossword-word-placement-response.interface.ts";

export interface CrosswordGridResponse {
    crosswordGameId: number;
    width: number;
    height: number;
    wordPlacements: CrosswordWordPlacementResponse[];
    isFinished: boolean;
    startedAt: Date;
    finishedAt: Date;
}