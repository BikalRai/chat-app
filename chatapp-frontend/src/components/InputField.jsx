import { useState } from "react";
import { MdOutlineVisibility, MdOutlineVisibilityOff } from "react-icons/md";

const InputField = ({ type = "text", label = "label" }) => {
  const [isVisible, setIsVisible] = useState(false);
  const [inputType, setInputType] = useState(type);

  const handlePasswordVisible = () => {
    setIsVisible((prev) => !prev);

    if (isVisible) setInputType("password");
    if (!isVisible) setInputType("text");
  };

  return (
    <div className='border border-gh-dark-50 rounded-4xl  outline-0 overflow-clip flex justify-between items-center focus-within:border-gh-primary hover:border-gh-primary pr-4'>
      <div className='grow'>
        <input
          className='border-0 outline-0 text-sm w-full px-4 py-2'
          type={inputType}
          id={label}
          name={label}
          placeholder={`${label[0].toUpperCase()}${label
            .slice(1)
            .toLowerCase()}`}
        />
      </div>
      {type === "password" &&
        (!isVisible ? (
          <MdOutlineVisibility
            className='cursor-pointer '
            onClick={handlePasswordVisible}
          />
        ) : (
          <MdOutlineVisibilityOff
            className='cursor-pointer '
            onClick={handlePasswordVisible}
          />
        ))}
    </div>
  );
};

export default InputField;
