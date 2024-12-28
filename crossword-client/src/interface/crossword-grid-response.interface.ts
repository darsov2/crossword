import {CrosswordWordPlacementResponse} from "@/interface/crossword-word-placement-response.interface.ts";

export interface CrosswordGridResponse {
    width: number;
    height: number;
    wordPlacements: CrosswordWordPlacementResponse[];
}