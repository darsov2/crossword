import React, { useState, useEffect } from 'react';
import { Card } from '@/components/ui/card';
import { Timer } from 'lucide-react';

const TimeTracker = ({ isSubmitted = false, startTime, endTime }) => {
    const [elapsedTime, setElapsedTime] = useState(0);

    // Convert string dates to Date objects if they exist
    const puzzleStartTime = startTime ? new Date(startTime) : null;
    const puzzleEndTime = endTime ? new Date(endTime) : null;

    useEffect(() => {
        console.log(isSubmitted)
        console.log(endTime)
        if (!puzzleStartTime) return;

        // If submitted, calculate final elapsed time
        if (isSubmitted && puzzleEndTime) {
            const finalTime = Math.floor((puzzleEndTime - puzzleStartTime) / 1000);
            setElapsedTime(finalTime);
            return;
        }

        // If not submitted, start the timer
        if (!isSubmitted) {
            const timer = setInterval(() => {
                const currentTime = Math.floor((new Date() - puzzleStartTime) / 1000);
                setElapsedTime(currentTime);
            }, 1000);

            return () => clearInterval(timer);
        }
    }, [puzzleStartTime, puzzleEndTime, isSubmitted]);

    const formatTime = (date) => {
        if (!date) return '--:--:--';
        return date.toLocaleTimeString('mk-MK', {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        });
    };

    const formatElapsedTime = (seconds) => {
        if (seconds < 0) return '00:00:00';
        const hrs = Math.floor(seconds / 3600);
        const mins = Math.floor((seconds % 3600) / 60);
        const secs = seconds % 60;
        return `${hrs.toString().padStart(2, '0')}:${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;
    };

    return (
        <div className="flex flex-col items-center space-y-2">
            <div className="flex items-center gap-2">
                <Timer className="w-5 h-5" />
                <span className="text-xl font-mono">
                    {formatElapsedTime(elapsedTime)}
                </span>
            </div>
            <div className="text-sm text-gray-500 space-y-1">
                <div>Почеток: {formatTime(puzzleStartTime)}</div>
                {isSubmitted && endTime && (
                    <div>Крај: {formatTime(puzzleEndTime)}</div>
                )}
            </div>
        </div>
    );
};

export default TimeTracker;