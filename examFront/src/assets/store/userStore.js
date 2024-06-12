import { create } from "zustand";

export const useUserStore = create((set) => ({
  firstName: "",
  lastName: "",
  email: "",
  userRole: "",
  setUser: (userData) =>
    set({
      firstName: userData.firstName,
      lastName: userData.lastName,
      email: userData.email,
      userRole: userData.userRole,
      token: userData.token,
    }),
}));