interface PaginationProps {
  currentPage: number;
  totalPages: number;
  setCurrentPage: (page: number) => void;
}

function Pagination({
  currentPage,
  totalPages,
  setCurrentPage,
}: PaginationProps) {
  if (totalPages <= 1) return null;

  return (
    <div className="flex justify-center mt-4 gap-2">
      {Array.from({ length: totalPages }).map((_, i) => (
        <button
          key={i}
          className={`px-3 py-1 rounded ${
            currentPage === i ? "bg-blue-600 text-white" : "bg-gray-200"
          }`}
          onClick={() => setCurrentPage(i)}
        >
          {i + 1}
        </button>
      ))}
    </div>
  );
}

export default Pagination;
