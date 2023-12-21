import axios from "axios";
import {ref} from "vue";
import {useRouter} from "vue-router";
import { useAuthStore } from '~/stores/auth'

const store = useAuthStore();
const { user } = store;

function stockFn() {

    const stocks = ref('');
    const router = useRouter();

    function getStocks() {
        axios.get('http://localhost:8080/api/stocks',
            {
                headers: {
                    Authorization: `Bearer ${user.token}`
                }
            }).then((result) => {
            stocks.value = result.data.data;
        });
    }

    function moveToDetailPage(id) {
        router.push(`${id}`)
    }

    function moveToRegistPage() {
        router.push(`regist`)
    }

    return {stocks, getStocks, moveToDetailPage, moveToRegistPage}
}

export { stockFn }