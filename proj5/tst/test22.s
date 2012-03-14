	.align 4
test_foo:
!locals=1, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore
	ret
	restore

	.align 4
test_bar:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 2)]
	mov 2,%l0
	st %l0,[%fp-4]
! [RETURN (VAR 1)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=2, max_args=2
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME test_foo) ( (PARAM 0) (CONST 1)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [1],%l1
	st %l1,[%sp+68]
	call test_foo
	nop
	mov %o0,%l2
!>> Temp t1 assigned to reg %l3
	mov %o0,%l3
! [MOVE (VAR 1) (TEMP 1)]
	mov %l3,%l2
	st %l2,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME test_bar) ( (PARAM 0) (CONST 1)))]
	ld [%fp+68],%l2
	st %l2,[%sp+68]
	ld [1],%l4
	st %l4,[%sp+68]
	call test_bar
	nop
	mov %o0,%l5
!>> Temp t2 assigned to reg %l6
	mov %o0,%l6
! [MOVE (VAR 2) (TEMP 2)]
	mov %l6,%l5
	st %l5,[%fp-8]
! [CALLST (NAME print) ( (VAR 1))]
	ld [%fp-4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 2))]
	ld [%fp-8],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  8
!Total insts: 53
