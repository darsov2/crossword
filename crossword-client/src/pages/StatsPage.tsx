import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Clock, Hash, User } from 'lucide-react';
import useGet from "@/hooks/useGet.ts";
import {useParams} from "react-router";

const StatsPage = () => {
  const { id } = useParams();
  const { data, isLoading } = useGet(`api/crossword/stats?id=${id}`);

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
  };

  return (
      <div className="min-h-screen bg-gray-50 p-4">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-3xl font-bold text-center text-gray-900 mb-8">Статистика</h1>

          {!isLoading && (
              <div className="bg-white rounded-lg shadow">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead className="font-semibold">
                        <div className="flex items-center gap-2">
                          <User className="h-4 w-4" />
                          Корисничко име
                        </div>
                      </TableHead>
                      <TableHead className="font-semibold">
                        <div className="flex items-center gap-2">
                          <Hash className="h-4 w-4" />
                          Број на погодоци
                        </div>
                      </TableHead>
                      <TableHead className="font-semibold">
                        <div className="flex items-center gap-2">
                          <Clock className="h-4 w-4" />
                          Време
                        </div>
                      </TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {data.map((game, index) => (
                        <TableRow key={index} className="hover:bg-gray-50">
                          <TableCell className="font-medium">{game.username}</TableCell>
                          <TableCell>{game.correctGuesses} / {game.totalWords}</TableCell>
                          <TableCell>{formatTime(game.timeTaken)}</TableCell>
                        </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
          )}
        </div>
      </div>
  );
};

export default StatsPage;