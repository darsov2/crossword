import {Button} from "@/components/ui/button.tsx";
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle
} from "@/components/ui/dialog.tsx";

export interface DialogProps {
    title: string;
    message: string;
    handleDialogResult: (x: boolean) => void,
    onOpenChange: (x: boolean) => void
}

const ConfirmSubmissionDialog = (props: DialogProps) => {
    return (
        <>
            <Dialog
                open={true}
                onOpenChange={props.onOpenChange}
            >
                <DialogContent className="bg-white sm:max-w-[425px]">
                    <DialogHeader>
                        <DialogTitle>{props.title}</DialogTitle>
                        <DialogDescription>
                            {props.message}
                        </DialogDescription>
                    </DialogHeader>
                    <DialogFooter className="flex justify-end gap-2">
                        <Button
                            variant="outline"
                            onClick={() => props.handleDialogResult(false)}
                        >
                            Откажи
                        </Button>
                        <Button
                            onClick={() => props.handleDialogResult(true)}
                            className="bg-gray-600 hover:bg-gray-700 text-white"
                        >
                            Потврди
                        </Button>
                    </DialogFooter>
                </DialogContent>
            </Dialog>
        </>
    )
}

export default ConfirmSubmissionDialog;