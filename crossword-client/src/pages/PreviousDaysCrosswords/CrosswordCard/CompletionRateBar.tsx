const CompletionRateBar = ({rate}) => {
    return (
        <div className="w-full bg-gray-200 rounded-full h-1">
            <div
                className="bg-blue-600 h-1 rounded-full"
                style={{width: `${rate}%`}}
            />
        </div>
    )
}

export default CompletionRateBar;