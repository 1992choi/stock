import { defineStore } from 'pinia'

interface IUser {
    email: string
    id: string
    name: string
    token: string
}

export const useAuthStore = defineStore('auth', {
    state: () => {
        return {
            user: {
                email: '',
                id: '',
                name: '',
                token: ''
            } as IUser
        }
    },
    persist: true,
    getters: {
        getEmail: (state): string => state.user.email,
        getId: (state): string => state.user.id,
        getName: (state): string => state.user.name,
        getToken: (state): string => state.user.token
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