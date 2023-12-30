import axios from "axios";
import {ref} from "vue";
import { useAuthStore } from '~/stores/auth'
import {useRouter} from "vue-router";

const store = useAuthStore();
const { user } = store;

function bookmarkFn() {

    const bookmarks = ref('');
    const router = useRouter();

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

    async function deleteBookmark(id) {
        await axios.delete(`http://localhost:8080/api/bookmarks/${id}`,
            {
                headers: {
                    Authorization: `Bearer ${user.token}`
                }
            }).then((response) => {
            if (response.data.status == 'success') {
                alert("삭제 성공");
                router.push('/bookmark/list');
            } else {
                alert(response.data.message);
            }
        }).catch((error) => {
            console.log(error.response)
            alert("삭제 실패");
        });
    }

    return {bookmarks, getBookmarks, deleteBookmark}
}

export { bookmarkFn }