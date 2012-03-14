	.align 4
test_foo:
!locals=0, max_args=1
	save %sp,-96,%sp
! [CJUMP <= (PARAM 1) (CONST 1) (NAME L0)]
	ld [%fp+72],%l0
	mov 1,%l1
	cmp %l0,%l1
	ble L0
	nop
! [MOVE (TEMP 1) (CALL (NAME test_bar) ( (PARAM 0)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	call test_bar
	nop
	mov %o0,%l1
!>> Temp t1 assigned to reg %l2
	mov %o0,%l2
! [RETURN (TEMP 1)]
	ret
	restore
! [JUMP (NAME L1)]
	ba L1
	nop
! [LABEL L0]
L0:
! [RETURN (CONST 3)]
	ret
	restore
! [LABEL L1]
L1:
	ret
	restore

	.align 4
test_bar:
!locals=0, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 2) (CALL (NAME test_foo) ( (PARAM 0) (CONST 1)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [1],%l1
	st %l1,[%sp+68]
	call test_foo
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [RETURN (TEMP 2)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 3) (CALL (NAME test_foo) ( (PARAM 0) (CONST 2)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [2],%l1
	st %l1,[%sp+68]
	call test_foo
	nop
	mov %o0,%l2
!>> Temp t3 assigned to reg %l3
	mov %o0,%l3
! [MOVE (VAR 1) (TEMP 3)]
	mov %l3,%l2
	st %l2,[%fp-4]
! [CALLST (NAME print) ( (VAR 1))]
	ld [%fp-4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 59
