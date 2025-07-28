import InputField from "../components/InputField";
import { Link } from "react-router-dom";
import PrimaryGHButton from "../components/PrimaryGHButton";
import GoogleGHButton from "../components/GoogleGHButton";

export default function Login() {
  return (
    <div className='flex flex-col bg-gh-primary min-h-dvh justify-center lg:flex-row lg:align-middle'>
      <div className='text-gh-light grid gap-3 p-5 md:flex md:flex-col md:justify-center '>
        <div className='logo'>
          <img src='./images/logo.png' alt='Logo' />
        </div>
        <p>Login to continue chatting with your friends and team.</p>
      </div>
      <form className='flex flex-col gap-5 justify-center h-fit bg-gh-card p-5 lg:shadow-2xl lg:rounded-4xl lg:p-12 lg:self-center lg:w-[450px]'>
        <h1 className='text-[32px] font-medium'>Login</h1>
        <InputField label='email' />
        <InputField type='password' label='password' />
        <Link className='text-xs text-gh-primary font-medium ms-auto'>
          Forgot password?
        </Link>
        <PrimaryGHButton />
        <div className='flex items-center gap-1.5'>
          <hr className='h-0.75 w-full border-0 bg-gray-300' />
          <p className='text-gray-400'>OR</p>
          <hr className='h-0.75 w-full border-0 bg-gray-300' />
        </div>
        <GoogleGHButton />
      </form>
    </div>
  );
}
