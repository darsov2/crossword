import {CrosswordGridSubmission} from "@/interface/crossword-grid-submission.interface.ts";

export interface CrosswordGameSubmissionInterface {
    crosswordGameId: number;
    answers: CrosswordGridSubmission[];
}