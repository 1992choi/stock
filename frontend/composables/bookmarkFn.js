import axios from "axios";
import {ref} from "vue";
import { useAuthStore } from '~/stores/auth'

const store = useAuthStore();
const { user } = store;

function bookmarkFn() {

    const bookmarks = ref('');

    function getBookmarks() {
        axios.get(`http://localhost:8080/api/users/${user.id}/bookmarks`,
            {
                headers: {
                    Authorization: `Bearer ${user.token}`
                }
            }).then((result) => {
                bookmarks.value = result.data.data;
            }
        );
    }

    return {bookmarks, getBookmarks}
}

export { bookmarkFn }