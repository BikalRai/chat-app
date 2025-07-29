import InputField from "../components/InputField";
import { Link } from "react-router-dom";
import PrimaryGHButton from "../components/PrimaryGHButton";
import GoogleGHButton from "../components/GoogleGHButton";
import OrSeperator from "../components/OrSeperator";

export default function Login() {
  return (
    <div className='flex flex-col bg-gh-primary min-h-dvh justify-center md:items-center lg:flex-row lg:items-center lg:gap-12'>
      <div className='text-gh-light grid gap-3 p-5 md:flex md:flex-col md:justify-center '>
        <div className='logo'>
          <img src='./images/logo.png' alt='Logo' />
        </div>
        <p>Login to continue chatting with your friends and team.</p>
      </div>
      <div className='p-4'>
        <form className='flex flex-col gap-5 justify-center rounded-2xl bg-gh-card p-8 lg:shadow-2xl lg:rounded-4xl lg:p-12 lg:self-center md:w-[450px] lg:w-[500px]'>
          <h1 className='text-[32px] font-medium'>Login</h1>
          <InputField label='email' />
          <InputField type='password' label='password' />
          <div className='flex justify-between align-middle text-xs  lg:text-sm'>
            <p>
              Don't have an account?{" "}
              <Link
                to='register'
                className='text-gh-primary transition hover:underline hover:font-medium'
              >
                Register
              </Link>
            </p>
            <Link className='text-gh-primary font-medium'>
              Forgot password?
            </Link>
          </div>
          <PrimaryGHButton buttonText='Login' />
          <OrSeperator />
          <GoogleGHButton btnText={`Login`} />
        </form>
      </div>
    </div>
  );
}
