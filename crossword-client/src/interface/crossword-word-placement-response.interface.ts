import {PositionResponse} from "@/interface/position-response.interface.ts";

export interface CrosswordWordPlacementResponse {
    x: number;
    y: number;
    clue: string;
    direction: string;
    cells: PositionResponse[];
    id: number;
}