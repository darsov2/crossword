export interface CrosswordWordPlacementResponse {
    x: number;
    y: number;
    clue: string;
    placement: string;
    positions: PositionResponse[];
}