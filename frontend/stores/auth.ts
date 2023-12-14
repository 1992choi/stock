interface IUser {
    email: string
    name: string
}

export const useAuthStore = defineStore('auth', {
    state: () => {
        return {
            user: {
                email: '',
                name: ''
            } as IUser
        }
    },
    getters: {
        getEmail: (state): string => state.user.email,
        getName: (state): string => state.user.name
    },
    actions: {
        setUser(data: IUser) {
            this.user = data
        }
    }
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useAuthStore, import.meta.hot))
}